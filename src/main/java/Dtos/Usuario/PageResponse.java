package Dtos.Usuario;

import java.util.List;

// Paginado genérico
public record PageResponse<T>(
        List<T> content,
        long total,
        int page,
        int size
) {}
