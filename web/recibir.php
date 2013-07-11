<?php 
/* print_r($_FILES);
echo'<br>';
echo 'nombre de la imagen: ';
print_r($_FILES['imagen']['name']);

echo'<br>';
echo 'tipo de la imagen: ';
print_r($_FILES['imagen']['type']);

echo'<br>';
echo 'ruta temporal de la imagen: ';
print_r($_FILES['imagen']['tmp_name']);
 */
$conexion=mysql_connect('localhost','root','asdf123') or die('No hay conexión a la base de datos');
$db=mysql_select_db('computadores',$conexion)or die('no existe la base de datos.');

$rutaEnServidor='imagenes';
$rutaTemporal=$_FILES['imagen']['tmp_name'];
$nombreImagen=$_FILES['imagen']['name'];
$rutaDestino=$rutaEnServidor.'/'.$nombreImagen;
move_uploaded_file($rutaTemporal,$rutaDestino);

$desc=$_POST['idPedido2'];

$sql="INSERT INTO comprobantes (imagen,idPedido) values('".$rutaDestino."','".$desc."')";
$res=mysql_query($sql,$conexion);

if ($res){
	echo 'Enviado con exito';
}else{
    echo 'no se puedo enviar';
} 

?>