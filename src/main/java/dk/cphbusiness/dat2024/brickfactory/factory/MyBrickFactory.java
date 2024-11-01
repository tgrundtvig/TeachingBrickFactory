package dk.cphbusiness.dat2024.brickfactory.factory;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.ArrayList;
import java.util.List;

public class MyBrickFactory implements BrickFactory
{
    private final JavaCSG csg;
    private final double unit;
    private final double knobDiameter;
    private final double knobHeight;
    private final double holeDiameterAdjust = -0.1;

    public MyBrickFactory(JavaCSG csg, double unit)
    {
        this.csg = csg;
        this.unit = unit;
        this.knobDiameter = (48.0/80.0)*unit;
        this.knobHeight = (30.0/80.0)*unit;
    }

    @Override
    public Brick createBasicBrick(int xSize, int ySize, int zSize)
    {
        return new BrickImpl(basicBrickGeometry(xSize, ySize, zSize));
    }

    @Override
    public Brick createLBrick(int xSize, int ySize, int width, int zSize)
    {
        Geometry3D xLeg = basicBrickGeometry(xSize, width, zSize);
        Geometry3D yLeg = basicBrickGeometry(width, ySize, zSize);
        Geometry3D res = csg.union3D(xLeg, yLeg);
        return new BrickImpl(res);
    }

    @Override
    public Brick createUBrick(int xSize, int ySize, int width, int zSize)
    {
        Geometry3D xLeg = basicBrickGeometry(xSize, width, zSize);
        Geometry3D yLeg = basicBrickGeometry(width, ySize, zSize);
        Geometry3D yLeg2 = csg.translate3DX((xSize-width)*unit).transform(yLeg);
        Geometry3D res = csg.union3D(xLeg, yLeg, yLeg2);
        return new BrickImpl(res);
    }

    @Override
    public Brick createOBrick(int xSize, int ySize, int width, int zSize)
    {
        Geometry3D xLeg = basicBrickGeometry(xSize, width, zSize);
        Geometry3D xLeg2 = csg.translate3DY((ySize-width)*unit).transform(xLeg);
        Geometry3D yLeg = basicBrickGeometry(width, ySize, zSize);
        Geometry3D yLeg2 = csg.translate3DX((xSize-width)*unit).transform(yLeg);
        Geometry3D res = csg.union3D(xLeg, xLeg2, yLeg, yLeg2);
        return new BrickImpl(res);
    }

    @Override
    public Brick createHBrick(int xSize, int ySize, int width, int midSectionOffset, int zSize)
    {
        Geometry3D xLeg = basicBrickGeometry(xSize, width, zSize);
        xLeg = csg.translate3DY(midSectionOffset*unit).transform(xLeg);
        Geometry3D yLeg = basicBrickGeometry(width, ySize, zSize);
        Geometry3D yLeg2 = csg.translate3DX((xSize-width)*unit).transform(yLeg);
        Geometry3D res = csg.union3D(xLeg, yLeg, yLeg2);
        return new BrickImpl(res);
    }


    private Geometry3D basicBrickGeometry(int xSize, int ySize, int zSize)
    {
        Geometry3D brick = csg.box3D(xSize*unit-0.1, ySize*unit-0.1, zSize*unit-0.1, false);
        brick = csg.translate3D(0.5*xSize*unit, 0.5*ySize*unit, 0).transform(brick);
        List<Geometry3D> addOns = new ArrayList<>();
        addOns.add(brick);
        List<Geometry3D> cutouts = new ArrayList<>();
        Geometry3D knob = createKnob();
        Geometry3D hole = createKnobHole();
        for(int x = 0; x < xSize; ++x)
        {
            for(int y = 0; y < ySize; ++y)
            {
                double xT = x*unit+0.5*unit;
                double yT = y*unit+0.5*unit;
                addOns.add(csg.translate3D(xT, yT, zSize*unit-0.1).transform(knob));
                cutouts.add(csg.translate3D(xT, yT, 0).transform(hole));
            }
        }
        Geometry3D res = csg.union3D(addOns);
        res = csg.difference3D(res, cutouts);
        return res;
    }

    private Geometry3D createKnob()
    {
        Geometry3D knob = csg.cylinder3D(knobDiameter, knobHeight, 64, false);
        Geometry3D knobTop = csg.cone3D(knobDiameter, knobDiameter - 0.2*knobDiameter, 0.1*knobDiameter, 64, false);
        knobTop = csg.translate3DZ(knobHeight).transform(knobTop);
        knob = csg.union3D(knob, knobTop);
        return knob;
    }

    private Geometry3D createKnobHole()
    {
        double diameter = knobDiameter + holeDiameterAdjust;
        double height = knobHeight+0.1*knobDiameter+0.8*knobDiameter;
        Geometry3D hole = csg.cylinder3D(diameter, height, 64, false);
        Geometry3D plate = csg.box3D(2.0*knobDiameter, 0.1*unit, height, false);
        List<Geometry3D> union = new ArrayList<>();
        union.add(hole);
        for(int i = 0; i < 4; ++i)
        {
            union.add(csg.rotate3DZ(csg.degrees(45*i)).transform(plate));
        }
        hole = csg.union3D(union);
        Geometry3D restrict = csg.cylinder3D(1.2*knobDiameter, knobHeight+0.1*knobDiameter, 64, false);
        Geometry3D restrictTop = csg.cone3D(1.2*knobDiameter, 0, 0.6*knobDiameter, 64, false);
        restrictTop = csg.translate3DZ(knobHeight+0.1*knobDiameter).transform(restrictTop);
        restrict = csg.union3D(restrict, restrictTop);
        hole = csg.intersection3D(hole, restrict);
        return hole;
    }

    public static void main(String[] args)
    {
        JavaCSG csg = JavaCSGFactory.createNoCaching();
        MyBrickFactory factory = new MyBrickFactory(csg, 8);
        //csg.view(factory.createLBrick(8,4,2, 1).getGeometry());
        csg.view(factory.createKnobHole());
    }
}
