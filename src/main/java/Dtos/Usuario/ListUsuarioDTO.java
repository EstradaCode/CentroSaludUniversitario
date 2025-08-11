package Dtos.Usuario;

public class ListUsuarioDTO {
        private Long id;
        private String username;
        private String email;
        private String rol;
        private boolean enabled;
        private String nombreCompleto; // derivado de Persona

        public ListUsuarioDTO(Long id, String username, String email, String rol, boolean enabled, String nombreCompleto) {
            this.id = id; this.username = username; this.email = email; this.rol = rol; this.enabled = enabled; this.nombreCompleto = nombreCompleto;
        }
        public ListUsuarioDTO() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }
}
