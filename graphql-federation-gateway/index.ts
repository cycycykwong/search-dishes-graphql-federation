import * as dotenv from 'dotenv';

import { ApolloServer } from 'apollo-server';
import { ApolloServerPluginInlineTrace } from "apollo-server-core";

import {
  ApolloGateway,
  IntrospectAndCompose,
} from '@apollo/gateway';

dotenv.config();

const hostService = process.env.HOST_NAME ?? '';
const port = process.env.HOST_PORT ?? '9000';
const restaurantService = process.env.RESTAURANT_SERVICE ?? '';
const dishService = process.env.DISH_SERVICE ?? '';

const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      {
        name: 'restaurant',
        url: `http://${restaurantService}/api/restaurant/graphql`,
      },
      {
        name: 'dish',
        url: `http://${dishService}/api/dish/graphql`,
      },
    ],
  }),
});

const server = new ApolloServer({
  gateway,
  plugins: [ApolloServerPluginInlineTrace()],
});

server.listen(port, hostService).then(({ url }) => {
  console.log(`Graphql Federation Gateway ready at ${url}`);
});
