package shazam;

import shazam.hash.CombineHash;
import shazam.hash.FFT;
import shazam.hash.Hash;
import shazam.pcm.PCM16MonoData;
import shazam.pcm.PCM16MonoParser;
import shazam.search.Grader;
import shazam.search.SongScore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the path of target .wav file at the next line:");
        Scanner in = new Scanner(System.in);
        File f = new File(in.nextLine().replaceAll("\"", ""));
        in.close();
        if (!f.exists()) {
            throw new RuntimeException("The file does not exist");
        }
    /*    if (f.isDirectory() || !f.getName().toLowerCase().endsWith(".wav")) {
            throw new RuntimeException("The file is not a .wav file");
        }*/

        long start = System.currentTimeMillis();

        System.out.println("Extracting fingerprints ...");

        ArrayList<Hash> hashes = CombineHash.generateFingerprint(f, -1);

        System.out.println("Finish extracting fingerprints, start grading");

        // call the grading module
        ArrayList<SongScore> scores = Grader.grade(hashes);

    /*    for (int i=0; i<5 && i<scores.size(); ++i) {
            System.out.println(scores.get(0));
        }*/
        long end = System.currentTimeMillis();
        if(scores.size()==0){
            System.out.println("没有查找到结果");
        }else{
        System.out.println("查找结果:"+scores.get(0));}


        System.out.printf("Time elapsed %.2f sec.\n", (end-start)/1000.0);
    }
}
