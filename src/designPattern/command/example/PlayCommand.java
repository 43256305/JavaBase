package designPattern.command.example;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 13:54
 **/
public class PlayCommand implements Command {
    private AudioPlayer audio;
    public PlayCommand(AudioPlayer audio) {
        this.audio = audio;
    }
    /**
     * 执行方法
     */
    @Override
    public void execute() {
        audio.play();
    }
}
