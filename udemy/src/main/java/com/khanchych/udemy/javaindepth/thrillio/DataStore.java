package com.khanchych.udemy.javaindepth.thrillio;


import com.khanchych.udemy.javaindepth.thrillio.constants.BookGenre;
import com.khanchych.udemy.javaindepth.thrillio.constants.Gender;
import com.khanchych.udemy.javaindepth.thrillio.entities.Bookmark;
import com.khanchych.udemy.javaindepth.thrillio.entities.User;
import com.khanchych.udemy.javaindepth.thrillio.entities.UserBookmark;
import com.khanchych.udemy.javaindepth.thrillio.services.BookmarkService;
import com.khanchych.udemy.javaindepth.thrillio.services.UserService;
import com.khanchych.udemy.javaindepth.thrillio.util.IOUtil;

public class DataStore {
    public static final int USER_BOOKMARK_LIMIT = 5;
    public static final int BOOKMARK_COUNT_PER_TYPE = 5;
    public static final int BOOKMARK_TYPES_COUNT = 3;
    public static final int TOTAL_USER_COUNT = 5;

    private static User[] users = new User[TOTAL_USER_COUNT];
    public static User[] getUsers() {
        return users;
    }

    private static Bookmark[][] bookmarks = new Bookmark[BOOKMARK_TYPES_COUNT][BOOKMARK_COUNT_PER_TYPE];
    public static Bookmark[][] getBookmarks() {
        return bookmarks;
    }

    private static UserBookmark[] userBookmarks = new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];
    private static int bookmarkIndex;

    public static void loadData() {
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    private static void loadUsers() {
        String[] data = new String[TOTAL_USER_COUNT];
        IOUtil.read(data, "./src/main/resources/User.txt");
        int rowNum = 0;
        for (String row : data) {
            String[] values = row.split("\t");

            int gender = Gender.MALE;
            if (values[5].equals("f")) {
                gender = Gender.FEMALE;
            } else if (values[5].equals("t")) {
                gender = Gender.TRANSGENDER;
            }

            users[rowNum++] = UserService.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
        }
    }

    private static void loadWebLinks() {
        String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
        IOUtil.read(data, "./src/main/resources/Web-Link.txt");
        int colNum = 0;
        for (String row : data) {
            String[] values = row.split("\t");
            bookmarks[0][colNum++] = BookmarkService.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/);
        }
    }

    private static void loadMovies() {
        String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
        IOUtil.read(data, "./src/main/resources/Movie.txt");
        int colNum = 0;
        for (String row : data) {
            String[] values = row.split("\t");
            String[] cast = values[3].split(",");
            String[] directors = values[4].split(",");
            bookmarks[1][colNum++] = BookmarkService.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
        }
    }

    private static void loadBooks() {
        bookmarks[2][0] = BookmarkService.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3);
        bookmarks[2][1] = BookmarkService.getInstance().createBook(4001, "Self-Reliance and Other Essays", 1993, "Dover Publications", new String[] {"Ralph Waldo Emerson"}, BookGenre.PHILOSOPHY, 4.5);
        bookmarks[2][2] = BookmarkService.getInstance().createBook(4002, "Light From Many Lamps", 1988, "Touchstone", new String[] {"Lillian Eichler Watson"}, BookGenre.PHILOSOPHY, 5.0);
        bookmarks[2][3] = BookmarkService.getInstance().createBook(4003, "Head First Design Patterns", 2004, "O'Reilly Media", new String[] {"Eric Freeman", "Bert Bates", "Kathy Sierra", "Elisabeth Robson"}, BookGenre.TECHNICAL, 4.5);
        bookmarks[2][4] = BookmarkService.getInstance().createBook(4004, "Effective Java Programming Language Guide", 2007, "Prentice Hall", new String[] {"Joshua Bloch"}, BookGenre.TECHNICAL, 4.9);

        String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
        IOUtil.read(data, "./src/main/resources/Book.txt");
        int colNum = 0;
        for (String row : data) {
            String[] values = row.split("\t");
            String[] authors = values[4].split(",");
            bookmarks[2][colNum++] = BookmarkService.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
        }
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks[bookmarkIndex] = userBookmark;
        bookmarkIndex++;
    }
}