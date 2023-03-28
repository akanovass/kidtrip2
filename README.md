# KIDTRIP

#### swagger url: http://localhost:8085/swagger-ui/index.html

#### There are 4 roles: 
   `[ADMIN,
    DRIVER,
    PARENT,
    CHILD]`

* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'ADMIN');`
* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'DRIVER');`
* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'PARENT');`
* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'CHILD');`


#### TripStatuses
`[NEW, ACCEPTED, DECLINED, WAITING, DRIVING, END]`

#### ApplicationStatus
`[NEW, ACCEPTED, DECLINED]`
