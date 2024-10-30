package dk.cphbusiness.dat2024.brickfactory.building.impl;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.building.Model;

import java.util.List;

public class ModelImpl implements Model
{
    private final List<Brick> bricks;

    public ModelImpl(List<Brick> bricks)
    {
        this.bricks = bricks;
    }

    @Override
    public List<Brick> getBricks()
    {
        return bricks;
    }
}
