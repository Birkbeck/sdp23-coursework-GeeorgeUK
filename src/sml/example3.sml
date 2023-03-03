        move EAX 1
        move EBX 4
        move ECX 1
        move EDX 2
retry:  subtract EBX EAX
        multiply ECX EDX
        notzero EBX retry
        print ECX