package com.project.couponsp2.services.login;

import com.project.couponsp2.beans.Category;
import com.project.couponsp2.exceptions.ErrMsg;
import com.project.couponsp2.exceptions.ExceptionSystem;
import com.project.couponsp2.impl.AdminServiceImpl;
import com.project.couponsp2.impl.CompanyServiceImpl;
import com.project.couponsp2.impl.CustomerServiceImpl;
import com.project.couponsp2.services.AdminService;
import com.project.couponsp2.services.ClientService;
import com.project.couponsp2.services.CompanyService;
import com.project.couponsp2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginManager {

    public final ApplicationContext ctx;

    private List<ClientService> loggedinClients = new ArrayList<>();

    public ClientService login(String email, String password, ClientType clientType) throws ExceptionSystem {

        switch (clientType){
            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                return ((CustomerServiceImpl) customerService).login(email,password) ? (ClientService) customerService : null;

            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                return ((CompanyServiceImpl) companyService).login(email,password) ? (ClientService) companyService : null;

            case ADMIN:
                AdminService adminService = ctx.getBean(AdminService.class);
                return ((AdminServiceImpl) adminService).login(email,password) ? (ClientService) adminService : null;

            default:
                break;
        }
        return null;
    }
}
