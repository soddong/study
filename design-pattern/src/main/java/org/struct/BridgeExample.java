package org.struct;

public class BridgeExample {
    public static void main(String[] args) {
        Remote radioRemote = new Remote(new Radio());
        radioRemote.togglePower();
        radioRemote.volumeUp();
        radioRemote.channelUp();

        System.out.println("-----");

        Remote tvRemote = new Remote(new TV());
        tvRemote.togglePower();
        tvRemote.volumeUp();
        tvRemote.channelDown();
    }
}

// 기능 계층
class Remote {
    protected final Device device;

    Remote(Device device) {
        this.device = device;
    }

    void togglePower() {
        if (device.isEnabled()) {
            device.disable();
            System.out.println("Device OFF");
        } else {
            device.enable();
            System.out.println("Device ON");
        }
    }

    void volumeDown() {
        device.setVolume(device.getVolume() - 1);
        System.out.println("Volume: " + device.getVolume());
    }

    void volumeUp() {
        device.setVolume(device.getVolume() + 1);
        System.out.println("Volume: " + device.getVolume());
    }

    void channelDown() {
        device.setChannel(device.getChannel() - 1);
        System.out.println("Channel: " + device.getChannel());
    }

    void channelUp() {
        device.setChannel(device.getChannel() + 1);
        System.out.println("Channel: " + device.getChannel());
    }
}

// 구현 계층
interface Device {
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int percent);
    int getChannel();
    void setChannel(int channel);
}

// 구현체 1: Radio
class Radio implements Device {
    private boolean enabled;
    private int volume = 10;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void enable() {
        enabled = true;
        System.out.println("Radio enabled");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("Radio disabled");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        this.volume = percent;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }
}

// 구현체 2: TV
class TV implements Device {
    private boolean enabled;
    private int volume = 20;
    private int channel = 5;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void enable() {
        enabled = true;
        System.out.println("TV enabled");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("TV disabled");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        this.volume = percent;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }
}
