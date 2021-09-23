import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class SimpleDocumentRequestStreamHandler extends SkillStreamHandler
{
    private static Skill getSkill()
    {
        return Skills.standard().addRequestHandlers(
          new LaunchIntentRequestHandler(),
          new HelloIntentRequestHandler()
        ).withSkillId("amzn1.ask.skill.232acf6c-a5df-4caf-8682-ca0ddb66b4b0").build();
    }

    public SimpleDocumentRequestStreamHandler()
    {
        super(getSkill());
    }
}
