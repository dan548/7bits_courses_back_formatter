package it.sevenbits.formatter;

public class Formatter {

    private static void indentWithFourSpacesNTimes(StringBuilder sb, int index, int n) {
        for (int i = 0; i < n; i++) {
            sb.insert(index, "    ");
        }
    }

    private static void removeSpacesNewlines(StringBuilder sb, int index) {
        while (sb.length() > index && (sb.charAt(index) == ' ' || sb.charAt(index) == '\n')) {
            sb.deleteCharAt(index);
        }
        if (sb.length() > index) sb.insert(index, "\n");
    }

    public static String format(String input) {
        StringBuilder sb = new StringBuilder(input.trim());

        int countOffset = 0;

        int i = 0;
        char x = sb.charAt(0);

        while (true) {
            while (x != ';' && x != '{' && x != '}') {
                i++;
                x = sb.charAt(i);
            }

            if (x == ';') {
                i++;
                removeSpacesNewlines(sb, i);
                i++;
                if(sb.charAt(i) == '}') countOffset--;
                indentWithFourSpacesNTimes(sb, i, countOffset);
                i += countOffset * 4;
            }

            if (x == '{') {
                if(sb.charAt(i-1) != ' ') {
                    sb.insert(i, " ");
                    i++;
                }
                i++;
                removeSpacesNewlines(sb, i);
                countOffset++;
                i++;
                if(sb.charAt(i) == '}') countOffset--;
                indentWithFourSpacesNTimes(sb, i, countOffset);
                i += countOffset * 4;
            }

            if (x == '}') {
                if (i >= sb.length() - 1) break;
                i++;
                removeSpacesNewlines(sb, i);
                i++;
                if(sb.charAt(i) == '}') countOffset--;
                indentWithFourSpacesNTimes(sb, i, countOffset);
                i += countOffset * 4;
            }

            x = sb.charAt(i);
        }


        return sb.toString();
    }

}
