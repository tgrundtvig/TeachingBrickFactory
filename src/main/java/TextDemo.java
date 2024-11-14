import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class TextDemo
{
    public static void main(String[] args)
    {
        JavaCSG csg = JavaCSGFactory.createNoCaching();

        Geometry2D text2D = csg.text2D("Hello", 8, 128);
        Geometry3D text3D = csg.linearExtrude(3, false, text2D);
        csg.view(text3D);
    }
}
