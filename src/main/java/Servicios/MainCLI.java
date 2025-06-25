package Servicios;

import Servicios.Negocio.UsuarioService;
import Servicios.Negocio.JornadaService;
import Modelo.Usuario;
import Utils.EntityMgmt;

import jakarta.persistence.EntityManager;
import java.util.Scanner;

public class MainCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityMgmt.getEntityManager();

        UsuarioService usuarioService = new UsuarioService(em);
        JornadaService jornadaService = new JornadaService(em);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menú CLI ASIS ---");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Buscar Usuario por ID");
            System.out.println("4. Eliminar Usuario por ID");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("DNI: ");
                    Long dni = Long.parseLong(scanner.nextLine());
                    System.out.print("Teléfono: ");
                    Long telefono = Long.parseLong(scanner.nextLine());
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Rol (ADMIN / SALUD / VISITANTE): ");
                    String rol = scanner.nextLine();
                    System.out.print("Matrícula: ");
                    Long matricula = Long.parseLong(scanner.nextLine());

                    Usuario nuevo = new Usuario(nombre, apellido, dni, telefono, username, password, email, rol, matricula);
                    usuarioService.crearUsuario(nuevo);
                    System.out.println("✔ Usuario creado.");
                    break;

                case 2:
                    usuarioService.listarUsuarios().forEach(u ->
                            System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getRol()));
                    break;

                case 3:
                    System.out.print("ID del usuario: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Usuario usuario = usuarioService.buscarUsuarioPorId(id);
                    if (usuario != null) {
                        System.out.println("✔ Usuario encontrado: " + usuario.getUsername());
                    } else {
                        System.out.println("❌ Usuario no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("ID del usuario a eliminar: ");
                    Long delId = Long.parseLong(scanner.nextLine());
                    usuarioService.eliminarUsuario(delId);
                    System.out.println("✔ Usuario eliminado.");
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("❌ Opción inválida.");
            }
        }

        em.close();
        EntityMgmt.closeFactory();
        scanner.close();
        System.out.println("👋 Saliste del CLI.");
    }
}
