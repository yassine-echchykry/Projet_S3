// Session_DAO.java
package com.picalti.DAO;

import com.picalti.beans.Session_Bean;

public interface Session_DAO {
    void insertSession(Session_Bean sessionBean);
    String getUsernameByToken(String token);
    String getUserIdByToken(String token);
}
