package com.xhh_study1.community.service;

import com.xhh_study1.community.dto.NotificationDTO;
import com.xhh_study1.community.dto.PaginationDTO;
import com.xhh_study1.community.enums.NotificationStatusEnum;
import com.xhh_study1.community.enums.NotificationTypeEnum;
import com.xhh_study1.community.exception.CustomizeErrorCode;
import com.xhh_study1.community.exception.CustomizeException;
import com.xhh_study1.community.mapper.NotificationMapper;
import com.xhh_study1.community.model.Notification;
import com.xhh_study1.community.model.NotificationExample;
import com.xhh_study1.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO=new PaginationDTO<>();
        Integer totalPage;

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = Integer.parseInt(String.valueOf(notificationMapper.countByExample(notificationExample)));

        if(totalCount % size == 0){
            totalPage=totalCount / size;
        }else{
            totalPage=totalCount / size +1;
        }

        if (page<1){
            page=1;
        }

        if (page>totalPage){
            page=totalPage;
        }


        paginationDTO.setPagination(totalPage,page);
        //size*(page-1)
        Integer offSet=size*(page-1);
        NotificationExample example=new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications=notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offSet,size));
        if (notifications.size()==0){
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long usrId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(usrId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return  notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);

        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        NotificationDTO notificationDTO = new NotificationDTO();
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKeySelective(notification);
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
