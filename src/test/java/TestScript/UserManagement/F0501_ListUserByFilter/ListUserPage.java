package TestScript.UserManagement.F0501_ListUserByFilter;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import TestScript.TermMajorPage;
import TestScript.UserManagement.UserElement;

public class ListUserPage extends TermMajorPage{
    // Chọn filter loại GV
    public void selectUserType(String type) {
        WebElement selectUserType = driver.findElement(UserElement.FILTER_USER_TYPE);
        selectUserType.click();

        Select selectedOption = new Select(selectUserType);
        selectedOption.selectByValue(type);
    }

    // Chọn filter vai trò GV
    public void selectUserRole(String role) {
        WebElement selectUserRole = driver.findElement(UserElement.FILTER_USER_ROLE);
        selectUserRole.click();

        Select selectedOption = new Select(selectUserRole);
        selectedOption.selectByValue(role);
    }

    // Lấy danh sách user
    public List<WebElement> getUsers() {
      WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(UserElement.USER_LIST));
      List<WebElement> usersItem = list.findElements(UserElement.USER_ITEM);
      return usersItem;
    }

    // Lấy loại giảng viên trong danh sách
    public List<String> getUserTypeInList() {
        List<String> userTypes = new ArrayList<>();
        List<WebElement> users = getUsers();
        for (WebElement userItem : users) {
            WebElement userTypeElement = userItem.findElement((UserElement.USER_TYPE_IN_LIST));
            userTypes.add(userTypeElement.getText());
        }
        return userTypes;
    }

    // Lấy vai trò giảng viên trong danh sách
    public List<String> getUserRoleInList() {
        List<String> userRoles = new ArrayList<>();
        List<WebElement> users = getUsers();
        for (WebElement userItem : users) {
            WebElement userRoleElement = userItem.findElement((UserElement.USER_ROLE_IN_LIST));
            userRoles.add(userRoleElement.getText());
        }
        return userRoles;
    }

}
