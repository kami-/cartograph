param(
    [string]
    [ValidateNotNullOrEmpty()]
    $m = $(throw "Map (-m) param required.")
#
#    [int]
#    [ValidateNotNullOrEmpty()]
#    $z = $(throw "Max zoom (-z) param required.")
)
$EMF_HOME = "./emf"
$IMAGEMAGICK_HOME = "C:/apps/imagemagick"
$GLAD_HOME = "C:/Program Files/GDAL"
$zoomLevels = ( 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072 )

& "$IMAGEMAGICK_HOME/convert" "$EMF_HOME/$m.emf" "$m.png"
$mapSizeString = gdal_translate -of vrt -expand rgba "$m.png" "$m.vrt"
$mapSize = ($mapSizeString -split ",")[1].Trim()
$maxZoomLevel = 0;
for ($i = 0; $i -le $zoomLevels.length - 1; $i++) {
    if ($zoomLevels[$i] -ge $mapSize) {
        $maxZoomLevel = $i
        break
    }
}
python "$GLAD_HOME/gdal2tiles.py" -p raster -z "1-$maxZoomLevel" -w none "$m.vrt"
rm "$m.vrt"
rm "$m.png"