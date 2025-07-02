package Utils.Enums;

public enum TipoEmpleo {

        INDUSTRIA("Industria manufacturera (fábrica)"),
        CONSTRUCCION("construcción"),
        COMERCIO("comercio"),
        TRANSPORTE("transporte (colectivo, taxi, remis, camión)"),
        SALUD("salud"),
        EMPLEO_PUBLICO("empleo público (municipio, provincia, nación)"),
        FUERZA_SEGURIDAD("fuerza de seguridad - seguridad privada - servicio penitenciario"),
        EMPLEO_PRIVADO("empleo privado"),
        SERVICIO_DOMESTICO("servicio doméstico - cuidado de personas"),
        SERVICIO_COMUNITARIO("servicios comunitarios - cooperativas - reciclado"),
        CHANGA("changa - venta ambulante"),
        CUENTAPROPISTA("cuentapropista"),
        OTROS("otros"),
        NO_SABE("no sabe o no contesta");

        private final String descripcion;

        TipoEmpleo(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static TipoEmpleo fromString(String value) {
            if (value == null || value.trim().isEmpty()) {
                return NO_SABE;
            }
            for (TipoEmpleo rama : TipoEmpleo.values()) {
                if (rama.descripcion.equalsIgnoreCase(value.trim())) {
                    return rama;
                }
            }
            throw new IllegalArgumentException("Valor no reconocido: " + value);
        }

        // Agrupamiento opcional para estadísticas
        public String getGrupo() {
            return switch (this) {
                case INDUSTRIA, CONSTRUCCION, COMERCIO, TRANSPORTE -> "Sector productivo";
                case SALUD, EMPLEO_PUBLICO, FUERZA_SEGURIDAD -> "Sector institucional";
                case EMPLEO_PRIVADO, SERVICIO_DOMESTICO, SERVICIO_COMUNITARIO -> "Sector privado no formalizado";
                case CHANGA, CUENTAPROPISTA -> "Autoempleo/Informal";
                case OTROS, NO_SABE -> "Otros";
            };
        }
    }

