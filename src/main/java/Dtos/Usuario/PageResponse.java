package Dtos.Usuario;

import java.util.List;

public class PageResponse<T> {

    private List<T> content; // Los datos de esta página
    private long total;      // Total de registros en la BD
    private int page;        // Número de página actual (0-based)
    private int size;        // Tamaño de página solicitado

    public PageResponse() {}

    public PageResponse(List<T> content, long total, int page, int size) {
        this.content = content;
        this.total = total;
        this.page = page;
        this.size = size;
    }

    // Getters y setters
    public List<T> getContent() { return content; }
    public void setContent(List<T> content) { this.content = content; }

    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}

