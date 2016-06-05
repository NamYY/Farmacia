
FOR %%F IN (expediente*.tex) DO SET NombreArchivo=%%F
pdflatex "%NombreArchivo%"
move expediente*.pdf C:\Users\NaMYY\Desktop\Farmacia\Expedientes
move expediente*.tex C:\Users\NaMYY\Desktop\Farmacia\Expedientes
del *.aux
del *.log