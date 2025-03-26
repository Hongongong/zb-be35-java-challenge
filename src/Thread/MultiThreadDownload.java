package Thread;


class DownloadRunnable implements Runnable {

    private final String fileName;

    DownloadRunnable(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public void run() {

        try {
            System.out.println("📥 " + fileName + " 다운로드 시작...");
            Thread.sleep(10000); // 10초 동안 다운로드한다고 가정
            System.out.println("✅ " + fileName + " 다운로드 완료!");
        } catch (InterruptedException e) {
            System.out.println("⚠ " + fileName + " 다운로드 중 오류 발생!");
        }


    }
}

public class MultiThreadDownload {

    public static void main(String[] args) {

        System.out.println("> Task : MultiThreadDownload.main()");

        String[] files = {"파일_1.zip", "파일_2.mp4", "파일_3.pdf"};

        for (String file : files) {
            Thread thread = new Thread(new DownloadRunnable(file));
            thread.start();
        }

    }

}
