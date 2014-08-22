/**
 * @author Jamie McGowan
 * @version 2014.8
 *
 * This models a thread for downloading, given a module code, year, term
 *
 */
import java.net.*;

public class Downloader {
    private int year;
    private String term;
    private String module;

    public Downloader(int year, String term, String module) {
        this.year = year;
        this.term = term;
        this.module = module;
    }
}
