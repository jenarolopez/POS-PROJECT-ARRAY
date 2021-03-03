/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

/**
 *
 * @author User
 */
public class UserVariables {

    public static int[] userId = new int[100];
    public static String[] username = new String[100];
    public static String[] password = new String[100];
    public static int[] isActive = new int[100];
    public static boolean[] isAdmin = new boolean[100];
    public static String[] firstname = new String[100];
    public static String[] middlename = new String[100];
    public static String[] lastname = new String[100];
    public static String[] contact = new String[100];
    public static String[] address = new String[100];
    public static String[] image = new String[100];
    public static String[] gender = new String[100];
    public static int currentUser = 0;

    UserVariables() {

        userId[0] = 0;
        username[0] = "Admin";
        password[0] = "Password";
        isActive[0] = 0;
        isAdmin[0] = true;
        firstname[0] = "Administrator";
        middlename[0] = "Bulacan";
        lastname[0] = "Bulsu";
        contact[0] = "0000";
        address[0] = "Bustos";
        String dir = System.getProperty("user.dir");
        dir = dir + "\\images\\admin.png";
        image[0] = dir;
        gender[0] = "Male";

       
    }

    public static String delete(int userId) {

        UserVariables.username[userId] = null;
        UserVariables.password[userId] = null;

        return "delete";
    }

    public static void saveusers(int isactive, int isadmin, int id) {
        UserVariables.isActive[id] = isactive;
        if (isadmin == 0) {
            UserVariables.isAdmin[id] = false;
        } else {
            UserVariables.isAdmin[id] = true;
        }
    }

    public static String[][] getusers() {

        int userLength = 0;
        for (int x = 0; x < UserVariables.username.length; x++) {
            if (UserVariables.username[x] != null) {
                userLength++;
            }
        }

        String[][] users = new String[userLength][5];
        int newCtr = 0;
        for (int y = 0; y < UserVariables.username.length; y++) {
            if (UserVariables.username[y] != null) {
                users[newCtr][0] = UserVariables.userId[y] + "";
                users[newCtr][1] = UserVariables.username[y] + "";
                users[newCtr][2] = UserVariables.password[y] + "";
                users[newCtr][3] = UserVariables.isAdmin[y] + "";
                users[newCtr][4] = UserVariables.isActive[y] + "";
                newCtr++;
            }
        }

        return users;
    }

    public static String changePasswordLogin(String username, String password, String oldpassword, String newpassword) {

        for (int x = 0; x < UserVariables.username.length; x++) {
            if (UserVariables.username[x] != null) {
                if (UserVariables.password[x].equals(password) && UserVariables.isAdmin[x] == true) {
                    UserVariables.password[UserVariables.currentUser] = newpassword;
                    return "change";
                }
            }

        }

        return "nomatch";
    }

    public static String Update(String firstname, String lastname, String middlename, String contact, String address, String image) {
        if (firstname.replaceAll(" ", "").equals("")
                || lastname.replaceAll(" ", "").equals("")
                || middlename.replaceAll(" ", "").equals("")
                || contact.replaceAll(" ", "").equals("")
                || address.replaceAll(" ", "").equals("")) {
            return "error";
        } else {
            UserVariables.firstname[currentUser] = firstname;
            UserVariables.middlename[currentUser] = middlename;
            UserVariables.lastname[currentUser] = lastname;
            UserVariables.contact[currentUser] = contact;
            UserVariables.address[currentUser] = address;
            UserVariables.image[currentUser] = image;
            return "updated";
        }
    }

    public static String Login(String username, String password) {
        if (username.replaceAll(" ", "").equals("") || password.replaceAll(" ", "").equals("")) {
            return "complete";
        } else {
            for (int ctr = 0; ctr < UserVariables.username.length; ctr++) {
                if (username.equals(UserVariables.username[ctr])) {
                    if (UserVariables.isActive[ctr] < 3) {
                        if (password.equals(UserVariables.password[ctr])) {
                            UserVariables.currentUser = ctr;
                            UserVariables.isActive[ctr] = 0;
                            return "log";
                        } else {
                            if (ctr != 0) {
                                UserVariables.isActive[ctr] = UserVariables.isActive[ctr] + 1;
                            }
                            return "nomatch";
                        }
                    } else {
                        return "blocked";
                    }

                }
            }
            return "error";
        }

    }

    public static String Register(String username, String password, String firstname, String lastname, String middlename, String contact, String address, String image, String gender) {
        int userId = 0;

        if (username.replaceAll(" ", "").equals("")
                || password.replaceAll(" ", "").equals("")
                || firstname.replaceAll(" ", "").equals("")
                || lastname.replaceAll(" ", "").equals("")
                || middlename.replaceAll(" ", "").equals("")
                || contact.replaceAll(" ", "").equals("")
                || address.replaceAll(" ", "").equals("")
                || gender.replaceAll(" ", "").equals("")) {
            return "complete";
        } else {
            for (int ctr = 0; ctr < UserVariables.username.length; ctr++) {
                if (username.equals(UserVariables.username[ctr])) {

                    return "used";

                }
            }
            for (int ctr = 0; ctr < UserVariables.userId.length; ctr++) {
                if (UserVariables.username[ctr] != null) {

                    userId = UserVariables.userId[ctr] + 1;

                }
            }
            UserVariables.userId[userId] = userId;
            UserVariables.username[userId] = username;
            UserVariables.password[userId] = password;
            UserVariables.firstname[userId] = firstname;
            UserVariables.middlename[userId] = middlename;
            UserVariables.lastname[userId] = lastname;
            UserVariables.address[userId] = address;
            UserVariables.contact[userId] = contact;
            UserVariables.image[userId] = image;
            UserVariables.isActive[userId] = 0;
            UserVariables.isAdmin[userId] = false;
            UserVariables.gender[userId] = gender;
            return "register";
        }

    }

    public static void main(String[] args) {
        ProductVariables c = new ProductVariables();
        UserVariables b = new UserVariables();
        Login a = new Login();
        a.setVisible(true);
    }

}
