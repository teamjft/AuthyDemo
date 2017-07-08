package authydemo

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/jft/registration/$action?/$id?(.${format})?"(controller: 'registration')
        "/jft/dashboard/$action?/$id?(.${format})?"(controller: 'dashboard')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
