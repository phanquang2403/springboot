package learn.youtobe.demo.services.impl;

import learn.youtobe.demo.base.BaseResponse;
import learn.youtobe.demo.base.CustomerException;
import learn.youtobe.demo.base.Message;
import learn.youtobe.demo.controllers.Request.InsertUserRequest;
import learn.youtobe.demo.controllers.Request.UserRequest;
import learn.youtobe.demo.controllers.response.UserResponse;
import learn.youtobe.demo.services.UserService;
import learn.youtobe.demo.services.daos.UserDAO;
import learn.youtobe.demo.services.dtos.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Log4j2
public class UserImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public UserResponse getListAllAccount(UserRequest request) {
        return userDAO.getListAccount(request);
    }

    @Override
    public BaseResponse createAccount(InsertUserRequest request) throws CustomerException {
        if (request == null) {
            throw new CustomerException(Message.REQUEST_INVALID);
        }
        if (request.getEmail() == null) {
            throw new CustomerException("Email is required");
        }

        if (request.getUsername() == null) {
            throw new CustomerException("Username is required");
        }

        if (request.getPass() == null) {
            throw new CustomerException("Pass is required");
        }


        Boolean isExitsAccount = userDAO.isExitsAccount(request);
        System.out.println("____________________isExitsAccount = " + isExitsAccount);

        if (isExitsAccount) {
            throw new CustomerException("username or email is exits");
        }

        return new BaseResponse(false, "Create user success!", userDAO.createAccount(request));

    }


    public byte[] exportExcel(Resource resource, UserRequest request) {
        UserResponse response = userDAO.getListAccount(request);
        return writeDataToExcelSalary(resource, response.getList());
    }

    private byte[] writeDataToExcelSalary(Resource resource, List<UserDTO> results) {
        try {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Workbook workbook = new XSSFWorkbook(resource.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            CreationHelper ch = workbook.getCreationHelper();
            CellStyle cStyle = workbook.createCellStyle();
            cStyle.setWrapText(true);
            cStyle.setDataFormat(ch.createDataFormat().getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"_);_(@_)"));
            Font font = workbook.createFont();
            font.setFontName("Times New Roman");
            font.setFontHeightInPoints((short) 13);
            cStyle.setFont(font);
            if (!results.isEmpty()) {
                for (int i = 0; i < results.size(); i++) {
                    UserDTO obj = results.get(i);
                    Row row = sheet.createRow(5 + i);
                    int column = 0;
                    row.createCell(column++).setCellValue(obj.getEmail());
                    row.createCell(column++).setCellValue(obj.getName());

                    System.out.println();

                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("WRITE_EXCEL_ERROR {}", e);
            log.error("exportExcelUser_error {0}", e);
            return new byte[]{};
        }
    }
}
