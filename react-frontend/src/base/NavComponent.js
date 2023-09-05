import { Nav } from "react-bootstrap"
import Container from "react-bootstrap/Container"
import Navbar from "react-bootstrap/Navbar"
import { Outlet, NavLink } from "react-router-dom"
import { Cart } from "../cart"


export function NavbarComponent(props){

    return (
        <>
            <Navbar bg="dark" variant="dark" expand="md" >
                <Container>
                    <Navbar.Brand href="/">
                        <h1>L&R TECH</h1>
                    </Navbar.Brand>
                    <Navbar.Toggle/>
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav variant="pills" defaultActiveKey="/" className="me-auto">
                            <NavLink to="/" className="nav-link">
                                Store
                            </NavLink>
                            <NavLink to="/about" className="nav-link">
                                About
                            </NavLink>
                        </Nav>
                    </Navbar.Collapse>
                    <Navbar.Text>
                        <Cart/>
                    </Navbar.Text>
                </Container>
            </Navbar>
        </>
    );
}