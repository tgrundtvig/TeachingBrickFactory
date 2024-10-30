union()
{
    translate([8.0, 8.0, 0.0])
    {
        linear_extrude(height = 8.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
        {
            scale([16.0, 16.0])
            {
                M8();
            }
        }
    }
    translate([0.0, 0.0, 8.0])
    {
        translate([8.0, 8.0, 0.0])
        {
            linear_extrude(height = 8.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
            {
                scale([16.0, 16.0])
                {
                    M8();
                }
            }
        }
    }
}

module M8()
{
    polygon
    (
        points =
        [
            [-0.5, -0.5], 
            [0.5, -0.5], 
            [0.5, 0.5], 
            [-0.5, 0.5]
        ],
        paths =
        [
            [0, 1, 2, 3]
        ]
    );
}
