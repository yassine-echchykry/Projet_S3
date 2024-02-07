// Session_DAO.java
package com.picalti.DAO;

import com.picalti.beans.Session_Bean;
import com.picalti.beans.UserBean;

public interface Session_DAO {
    void insertSession(Session_Bean sessionBean);
    UserBean getUserByToken(String token);
}
