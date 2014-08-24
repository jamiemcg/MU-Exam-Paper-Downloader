/**
 * @author Jamie McGowan
 * @version 2014.8
 *
 * This models a thread for downloading, given a module code, year, term
 * 
 *  !!!!NOTE: A lot of repeated code, refactor, write method that accepts year, term, module (reduce repetition)
 */
import java.io.*;
import java.net.*;

public class Downloader extends Thread{
    private int year;
    private String module;
    private File location;
    private boolean downloadRepeats;

    public Downloader(int year, String module, File location, boolean downloadRepeats) {
        this.year = year;
        this.module = module;
        this.location = location;
        this.downloadRepeats = downloadRepeats;
    }

    public void run() {
        String aut = "Autumn";
        String jan = "January";
        String sum = "Summer";
        String main = "Main";
        String repeat = "Repeat";

        File dir = new File(location, module);
        if( !dir.exists() ) {
            dir.mkdir(); //Create the directory if it doesn't already exist
        }

        //Try to download the exam papers (jan, sum, aug, main, repeat)
        String path = dir.getAbsolutePath();
        String url = "http://www.nuim.ie/expert2/downloads//";
        try {
            String fileNameOnline = String.valueOf(year) + "-" + module + "-"+ jan + ".pdf";
            URL web = new URL(url + fileNameOnline);
            String fileName = module + " " + String.valueOf(year) + " " + jan + ".pdf";

            BufferedInputStream in = new BufferedInputStream(web.openStream());
            FileOutputStream out = new FileOutputStream(path + "//" + fileName);

            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);
            }
            in.close();
            out.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String fileNameOnline = String.valueOf(year) + "-" + module + "-"+ sum + ".pdf";
            URL web = new URL(url + fileNameOnline);

            String fileName = module + " " + String.valueOf(year) + " " + sum + ".pdf";

            BufferedInputStream in = new BufferedInputStream(web.openStream());
            FileOutputStream out = new FileOutputStream(path + "//" + fileName);

            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);
            }

            in.close();
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(downloadRepeats) {
            try {

                String fileNameOnline = String.valueOf(year) + "-" + module + "-" + aut + ".pdf";
                URL web = new URL(url + fileNameOnline);

                String fileName = module + " " + String.valueOf(year) + " " + aut + ".pdf";

                BufferedInputStream in = new BufferedInputStream(web.openStream());
                FileOutputStream out = new FileOutputStream(path + "//" + fileName);

                byte[] data = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    out.write(data, 0, count);
                }

                in.close();
                out.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(year <= 2007) { //No longer named repeat and main after ~2007
            try {

                String fileNameOnline = String.valueOf(year) + "-" + module + "-"+ main + ".pdf";
                URL web = new URL(url + fileNameOnline);

                String fileName = module + " " + String.valueOf(year) + " "+ main + ".pdf";

                BufferedInputStream in = new BufferedInputStream(web.openStream());
                FileOutputStream out = new FileOutputStream(path + "//" + fileName);

                byte[] data = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    out.write(data, 0, count);
                }

                in.close();
                out.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(downloadRepeats) {
                try {

                    String fileNameOnline = String.valueOf(year) + "-" + module + "-" + repeat + ".pdf";
                    URL web = new URL(url + fileNameOnline);

                    String fileName = module + " " + String.valueOf(year) + " " + repeat + ".pdf";

                    BufferedInputStream in = new BufferedInputStream(web.openStream());
                    FileOutputStream out = new FileOutputStream(path + "//" + fileName);

                    byte[] data = new byte[1024];
                    int count;
                    while ((count = in.read(data, 0, 1024)) != -1) {
                        out.write(data, 0, count);
                    }

                    in.close();
                    out.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
