# PROJETO-JAVA-BACKEND

# Como executar o projeto

```
docker compose up -d postgres
```

Crie um arquivo economizae/.env com as variáveis de ambiente a seguir assim como no .env.example:
```
OPENAI_API_KEY=sk-xxxx
SERVER_DATABASE_HOST=localhost
```

```
cd economizae
./gradlew bootRun
```

Após inserir qualquer dependencia nova no build.gradle:
```
./gradlew build --refresh-dependencies
```

Após inserir qualquer teste novo
```
./gradlew clean test --tests
```

Hot Reload:

Terminal 1:
```
./gradlew -t classes
```

Terminal 2:
```
./gradlew bootRun
```