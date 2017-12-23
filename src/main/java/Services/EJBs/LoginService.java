package Services.EJBs;

import Models.JPAentities.UsersEntity;

public interface LoginService {
    UsersEntity verifyUser(UsersEntity usersEntity);
    boolean saveUpdateUser(UsersEntity usersEntity);
}
