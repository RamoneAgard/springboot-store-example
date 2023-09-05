import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row"


export function About(props){

    return <Container>
        <Row>
            <h3> About this Project: </h3>
        </Row>
        <br/>
        <Row>
            <p>
                This example project is a demonstration of a React.js front-end web store interacting with a spring-boot back-end api.
                This allows users to add products to their cart, remove them from their cart, and upon completing their order they are
                redirected to a stripe checkout session (cards with not be charged, and user data is only stored on local machine). For
                the purposes of this example, data is stored in a H2 in-memory database, product data is added to this database on startup,
                and image urls are served from and open source image api. The products displayed are simple examples of possible 3D printed
                items with options of the type of plactic to print with and the colors associated with those plastics. The React project uses react-router-dom
                and react-bootstrap, while the spring-boot project integrates with the Stripe maven dependency. To run this project locally, a Stripe
                account is needed, and the provided private key would need to be added to the application.properties file, as well as the webhook secret
                for confirming checkout sessions. This example web store was created with the goal to demonstrate functionality over complete visual polish.
                I created this example project as a means to show case a possible data flow for a small web store, and I wanted to incorporate my
                love of 3D printing into the topic.
            </p>
        </Row>
    </Container>
}
