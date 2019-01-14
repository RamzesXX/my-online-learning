public class APIResponseParser {

    /**
     * Parses the input text and returns a Book instance containing
     * the parsed data.
     * @param response text to be parsed
     * @return Book instance containing parsed data
     */
    public static Book parse(String response) {
        Book book = new Book();

        String value = parse(response, "title");
        book.setTitle(value);
        value = parse(response, "name");
        book.setAuthor(value);
        value = parse(response, "original_publication_year");
        book.setPublicationYear(Integer.valueOf(value));
        value = parse(response, "ratings_count");
        value = value.replaceAll(",", "");
        book.setRatingsCount(Integer.valueOf(value));
        value = parse(response, "average_rating");
        book.setAverageRating(Double.valueOf(value));
        value = parse(response, "image_url");
        book.setImageUrl(value);

        return book;
    }

    private static String parse(String response, String rule) {
        String value = null;
        String startRule = "<" + rule;
        String endRule = "</" + rule;
        int endIndex;
        int startIndex;

        if (response == null) return value;
        startIndex = response.indexOf(startRule);
        if (startIndex < 0) return value;
        startIndex = response.indexOf(">", startIndex);
        if (startIndex < 0) return value;
        startIndex++;
        endIndex = response.indexOf(endRule, startIndex);
        if (endIndex < 0) return value;

        return response.substring(startIndex, endIndex);
    }

    // write overloaded parse method with the 3 parameters response, startRule, endRule

    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
                "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";

        APIResponseParser.parse(response);
    }
}