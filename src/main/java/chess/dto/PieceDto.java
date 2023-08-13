package chess.dto;

public class PieceDto {

    private final String type;
    private final String color;
    private final String file;
    private final String rank;

    public PieceDto(final String type, final String color, final String file, final String rank) {
        this.type = type;
        this.color = color;
        this.file = file;
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getFile() {
        return file;
    }

    public String getRank() {
        return rank;
    }
}
