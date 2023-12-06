<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription</title>
</head>
<body>
    <h2>Inscription</h2>
    <form action="register" method="post">
        <!-- Champs du formulaire -->
        <label for="fullName">Nom Complet:</label>
        <input type="text" id="fullName" name="fullName" required><br>

        <label for="cin">CIN:</label>
        <input type="text" id="cin" name="cin" required><br>

        <label for="age">Âge:</label>
        <input type="text" id="age" name="age" required><br>

        <label for="sexe">Sexe:</label>
        <input type="text" id="sexe" name="sexe" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="password">Mot de passe:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="tele">Téléphone:</label>
        <input type="text" id="tele" name="tele" required><br>

        <!-- Bouton de soumission -->
        <input type="submit" value="S'inscrire">
    </form>
</body>
</html>
