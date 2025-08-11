package Dtos.Usuario;

// dto/PasswordChangeDTO.java
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class PasswordChangeDTO {

    @Schema(description = "Contraseña actual (obligatoria si el propio usuario cambia su contraseña). Para ADMIN es opcional.", example = "miClaveActual123")
    private String currentPassword;

    @NotBlank
    @Schema(description = "Nueva contraseña", example = "NuevaClave!2025")
    private String newPassword;

    public PasswordChangeDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
