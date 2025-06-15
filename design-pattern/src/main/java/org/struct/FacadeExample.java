package org.struct;

public class FacadeExample {
    public static void main(String[] args) {
        ComputerFacade facade = new ComputerFacade();
        facade.startComputer();
    }
}

class CPU {
    void freeze(){
        System.out.println("cpu freezing...");
    };
    void jump(long pos) {
        System.out.println("cpu " + pos + " jumping...");
    }
    void execute() {
        System.out.println("cpu execute...");
    }
}

class Memory {
    public void load(long pos, byte[] data) {
        System.out.println("memory " + pos + " loading... (" + data.length + " bytes)");
    }
}

class SSD {
    public byte[] read(long lba, int size) {
        System.out.println("ssd " + lba + " reading... (" +  size + " bytes)");
        return new byte[size];
    }
}

class ComputerFacade {
    private final CPU cpu;
    private final Memory memory;
    private final SSD ssd;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.ssd = new SSD();
    }

    public void startComputer() {
        System.out.println("============= boot start ============");
        cpu.freeze();
        byte[] bootData = ssd.read(0, 1024);
        memory.load(0, bootData);
        cpu.jump(0);
        cpu.execute();
        System.out.println("============= boot complete ============");
    }
}

