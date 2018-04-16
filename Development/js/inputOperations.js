function changeImgNameFile () {
	var inputFile = document.getElementById("imgFile");
	var outputFile = document.getElementById("txtImgFile");


	if (inputFile.value == "Nenhum arquivo selecionado" || inputFile.value == "")
		outputFile.innerHTML = "Nenhum arquivo selecionado";
	else 
		outputFile.innerHTML = inputFile.value.split('\\').pop();
}