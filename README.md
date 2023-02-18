# NFP121: Projet Finale (documentation)

> Instructors: Dr. Chadi Kallab & Dr. Adel Awada
> Executed by: Anthony Bassam MILAN 2747n - FAWAZ Mohamad

### But du Projet :

Ce projet vise à créer une simulation d’une agence de tourisme avec tableau de bord Client/Administrateur.
L’Administrateur a la capacité de créer et de mettre à jour les méthodes de transport, les logements ainsi que les activités. En outre, l’administrateur
peut rejoindre ces trois champs pour créer un programme.
Les clients peuvent faire des réservations dans le cadre de ces programmes, selon le montant d’argent qu’ils ont dans leur compte.
Les administrateurs peuvent enfin gérer ces réservations en les confirmant et/ou en les annulant

### Patrons de Conception :

Les patrons de conception mis en œuvre dans ce projet sont les suivants:

- Singleton
  Dans les files `ManageData` et `FileReaderFactory`, qui vont de pair parce que nous avons vu la nécessité de créer une seule entité de lecture/ecriture de données, ce qui réduirait la consommation de mémoire.
  En raison du fait que nous n’avons pas créé de base de données utilisateur, nous nous sommes plutôt appuyés sur un seul point d’entrée client: nous avons également mis en œuvre le modèle Singleton au modèle `User`.

- Factory
  Dans `FileReaderFactory`, l’accès aux fichiers et la lecture des données se feraient de manière dynamique, nous y avons donc ajouté le patron de conception Factory, afin d’augmenter sa réutilisabilité.

- MVC
  Le patron de conception `MVC` nous a aidés à organiser et à suivre la logique d’affaires :
  Nous avons commencé par créer des Models, implémenter les Controllers et finalement rendre les Views.
  Cela s’est avéré utile lorsque nous utilisions la méthode modèle pour afficher des vues avec une structure similaire - template method.

- Template Method
  Face au défi d’avoir du code répété, nous avons plutôt créé le modèle `SimpleView` qui nous soulagerait de tout le code standard, et nous aiderait à créer facilement les vues `Transports`, `Hébergements` et `Activités`

- Observer
  Lors de la création du formulaire de réservation, nous avons décidé de mettre en œuvre la méthode Observateur, qui nous aiderait à détecter le changement dans le nombre de réservations, obtenir le prix du programme sélectionné et le multiplier pour recevoir le prix total final qui changerait programmatiquement.

### Répartition des tâches :

Le code a été écrit par Anthony Milan (2747n), et la version initiale des diagrammes a été généré par Mohamad puis édité par Anthony Milan.
