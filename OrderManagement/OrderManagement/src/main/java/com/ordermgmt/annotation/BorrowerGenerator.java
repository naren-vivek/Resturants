package com.ordermgmt.annotation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

public class BorrowerGenerator extends IdentityGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		try {
			PreparedStatement ps = connection.prepareStatement("select substr(max(card_no), 3, 6) as id from borrower");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = Integer.parseInt(rs.getString("id")) + 1;
				String code = String.format("ID%1$" + 6 + "s", id).replace(' ', '0');
				return code;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ID000001";
	}

}
