package com.app.service;

import com.app.entities.Admin;



public interface AdminService {

	 Admin getAdminByEmail(String email);

	Admin addNewAdmin(Admin Admin);
}
