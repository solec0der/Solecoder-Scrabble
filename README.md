# Solecoder Scrabble 

![Build](https://github.com/solec0der/Solecoder-Scrabble/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

## Table of Contents

* [Prerequisites](#prerequisites)
    * [Keycloak](#keycloak)
    * [PostgreSQL](#postgresql)

## Prerequisites

In order to get this application up and running, some services have to be installed and configured.

### Keycloak

The authentication is handled by Keycloak, so a Keycloak-Server has to be up and running and configured. To install it with docker, follow these steps:

```shell script
# This will create a docker container with a keycloak instance. Username and Password for the admin interface are also configurable.

$ docker run -d -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin jboss/keycloak
```

Once keycloak is up and running, create a realm (in this case it is called solecoder-core-realm) and create a client using the json below.
Then simply adapt the settings in ch/solecoder/scrabble/config/application.yml in the keycloak-section.

```json
{
    "clientId": "solecoder-scrabble-core",
    "surrogateAuthRequired": false,
    "enabled": true,
    "clientAuthenticatorType": "client-secret",
    "redirectUris": [
        "http://localhost:9090/*"
    ],
    "webOrigins": [],
    "notBefore": 0,
    "bearerOnly": false,
    "consentRequired": false,
    "standardFlowEnabled": true,
    "implicitFlowEnabled": false,
    "directAccessGrantsEnabled": true,
    "serviceAccountsEnabled": true,
    "authorizationServicesEnabled": true,
    "publicClient": false,
    "frontchannelLogout": false,
    "protocol": "openid-connect",
    "attributes": {
        "saml.assertion.signature": "false",
        "saml.force.post.binding": "false",
        "saml.multivalued.roles": "false",
        "saml.encrypt": "false",
        "login_theme": "keycloak",
        "saml.server.signature": "false",
        "saml.server.signature.keyinfo.ext": "false",
        "exclude.session.state.from.auth.response": "false",
        "saml_force_name_id_format": "false",
        "saml.client.signature": "false",
        "tls.client.certificate.bound.access.tokens": "false",
        "saml.authnstatement": "false",
        "display.on.consent.screen": "false",
        "saml.onetimeuse.condition": "false"
    },
    "authenticationFlowBindingOverrides": {},
    "fullScopeAllowed": true,
    "nodeReRegistrationTimeout": -1,
    "protocolMappers": [
        {
            "name": "Client Host",
            "protocol": "openid-connect",
            "protocolMapper": "oidc-usersessionmodel-note-mapper",
            "consentRequired": false,
            "config": {
                "user.session.note": "clientHost",
                "id.token.claim": "true",
                "access.token.claim": "true",
                "claim.name": "clientHost",
                "jsonType.label": "String"
            }
        },
        {
            "name": "Client ID",
            "protocol": "openid-connect",
            "protocolMapper": "oidc-usersessionmodel-note-mapper",
            "consentRequired": false,
            "config": {
                "user.session.note": "clientId",
                "id.token.claim": "true",
                "access.token.claim": "true",
                "claim.name": "clientId",
                "jsonType.label": "String"
            }
        },
        {
            "name": "Client IP Address",
            "protocol": "openid-connect",
            "protocolMapper": "oidc-usersessionmodel-note-mapper",
            "consentRequired": false,
            "config": {
                "user.session.note": "clientAddress",
                "id.token.claim": "true",
                "access.token.claim": "true",
                "claim.name": "clientAddress",
                "jsonType.label": "String"
            }
        }
    ],
    "defaultClientScopes": [
        "web-origins",
        "role_list",
        "profile",
        "roles",
        "email"
    ],
    "optionalClientScopes": [
        "address",
        "phone",
        "offline_access",
        "microprofile-jwt"
    ],
    "access": {
        "view": true,
        "configure": true,
        "manage": true
    }
}
```

### PostgreSQL

This service uses PostgreSQL as a database system. To install PostgreSQL using Docker, follow the steps below:

```shell script
# This will create a docker container with a postgresql instance. the root password can be set too.

$ docker run --name pg-docker -e POSTGRES_PASSWORD=root -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data  postgres
```

After that, a new database *solecoder* has to be created. In that database, create a new schema *scrabble*.

```sql
-- To create the schema
CREATE SCHEMA scrabble;
```


