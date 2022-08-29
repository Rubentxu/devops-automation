export interface User {
  id: string
  name: string;
  lastname: string;
  email: string;
  securityContext: SecurityContext
};

export interface Group {
  name: string
}

export interface AutomatedTask {
  id: ID;
  name: string;
  description: string;
  priority: number;
  developer_team: string[];
  tags: Tag[];
  entry_point: EntryPoint;
  created: string
  securityContext: SecurityContext
}

export interface AutomatedTaskRun extends AutomatedTask {
  user: User;
}

export interface SecurityContext {
  roles: Role[]
  reviewers: User[];
}

export interface Role {
  id :number
  name: string
}

export interface Tag {
  name: string
  value: string
}

export interface EntryPoint {
  command: string[]
}

export interface ID {
  value: string
}
