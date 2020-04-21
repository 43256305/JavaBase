package designPattern.command.example;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 13:55
 **/
public class StopCommand implements Command {
    private AudioPlayer audio;
    public StopCommand(AudioPlayer audio) {
        this.audio = audio;
    }
    /**
     * 执行方法
     */
    @Override
    public void execute() {
        audio.stop();
    }
}
