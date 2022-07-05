package greeting;

class Name {
    String value;
    boolean isUpperCase;

    public Name(String value) {
        this.value = value;
        this.isUpperCase = isUpperCase(value);
    }

    private boolean isUpperCase(String name) {
        if (isNonLetterCharacter(name)) {
            return false;
        }
        return name.equals(name.toUpperCase());
    }

    private boolean isNonLetterCharacter(String name) {
        return name.equals(name.toLowerCase()) && name.equals(name.toUpperCase());
    }
}
