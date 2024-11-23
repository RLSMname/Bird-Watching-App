enum Errors {
  noBirds,
  notFound,
  birdAlreadyExists;
}

extension ErrorsText on Errors {
  String get text {
    switch (this) {
      case Errors.noBirds:
        return "No birds found";
      case Errors.notFound:
        return "There is no bird with this id";
      case Errors.birdAlreadyExists:
        return "There already exists a bird with this name or id";
    }
  }
}
