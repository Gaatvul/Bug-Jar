package com.gaatvul.bugtracker.DTOs;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdatePasswordDTO {

    @NotEmpty(message = "Uh oh, looks like there's no password filled in!")
    @NotNull(message = "Uh oh, looks like this field is null")
    private String oldPassword;

    @NotEmpty(message = "Uh oh, looks like there's no new password filled in!")
    @NotNull(message = "Uh oh, looks like this field is null")
    private String newPassword;

    @NotEmpty(message = "Uh oh, looks like there's no confirm password filled in!")
    @NotNull(message = "Uh oh, looks like this field is null")
    private String confirmNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public boolean isMismatchedWithConfirm() {
        return (!newPassword.equals(confirmNewPassword));
    }

}
