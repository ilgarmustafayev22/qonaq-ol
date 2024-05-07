package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.dao.entity.UserEntity;

public interface LikeService {

    void toggleLike(long eventId, long userId);

}
