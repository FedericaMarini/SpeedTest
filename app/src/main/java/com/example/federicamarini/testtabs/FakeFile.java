package com.example.federicamarini.testtabs;

import android.util.Log;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class FakeFile {
    private static final int DEFAULT_SIZE = 1000 * 1000000 * 15 / 8; // 1000 Mbps for 15 seconds;
    private int size;
    private Random random;

    public FakeFile() {
        this.size = DEFAULT_SIZE;
    }

    public FakeFile(int size) {
        if (size > 0) {
            this.size = size;
        } else {
            this.size = DEFAULT_SIZE;
        }
        random = new Random();
    }

    public String read(int numBytes) {
        size -= numBytes;
        String result;
        if (size >= 0) {
            //stringa random di lunghezza numBytes
            result = RandomStringUtils.random(numBytes);
            Log.d("FakeFile", "String read "+result);
            return result;
        } else {
            return null;
        }
    }

    public byte[] readBytes(int numBytes) {
        if (size >= numBytes) {
            size -= numBytes;
            byte[] randBytes = new byte[numBytes];
            random.nextBytes(randBytes);
            Log.d("FakeFile", "Byte read "+randBytes);
            return randBytes;
        } else {
            return null;
        }
    }

    public int getSize() {
        return size;
    }

}
