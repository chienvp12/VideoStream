/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author Nguyen Van Toan
 */
public class Data implements Serializable{
    private String name;
    private boolean video;
    private boolean sound;
    private byte[] img;
    private byte[] voice;

    public Data(String name, byte[] img) {
        this.name = name;
        this.img = img;
    }

    public Data(String name, boolean video, boolean sound) {
        this.name = name;
        this.video = video;
        this.sound = sound;
    }

    public Data(String name) {
        this.name = name;
    }

    public byte[] getVoice() {
        return voice;
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Data() {
    }
}
