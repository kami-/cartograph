ls emf | foreach {
    echo "Generating tiles for map $_.BaseName."
    ./generate-map-tiles.ps1 -m $_.BaseName
    echo "Done map $_.BaseName."
}